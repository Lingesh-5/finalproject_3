package com.example.hotelBookingSystem.services;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotelBookingSystem.configs.RazorpayProps;
import com.example.hotelBookingSystem.models.Payment;
import com.example.hotelBookingSystem.repositories.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {
    
    @Autowired(required=false)
    final private RazorpayClient client;
    @Autowired(required=false)
    final private RazorpayProps props;
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentService(RazorpayProps props) throws RazorpayException {
        this.props = props;
        this.client = new RazorpayClient(props.getKeyId(), props.getSecret());
    }

    public Map<String, Object> createOrder(long amountInRupees, String currency, String receipt,
            Map<String, String> notes) throws RazorpayException {

        long amountPaise = amountInRupees * 100L;
        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", amountPaise);
        orderReq.put("currency", currency);
        orderReq.put("receipt", receipt);
        orderReq.put("payment_capture", 1);
        if (notes != null && !notes.isEmpty()) {
            orderReq.put("notes", new JSONObject(notes));
        }

        Order order = client.orders.create(orderReq);

        Payment payment = new Payment();
        payment.setBookingId(order.get("id").toString());
        payment.setAmountInRupees(amountInRupees);
        payment.setStatus("CREATED");
        payment.setUserId(notes != null ? notes.get("userId") : "none");
        paymentRepository.save(payment);

        return Map.of("orderId", order.get("id"), "amount", order.get("amount"), "currency", order.get("currency"), "key",
                props.getKeyId());
    }

    public boolean verifyPaymentSignature(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
        try {
            String data = razorpayOrderId + '|' + razorpayPaymentId;
            String computed = hmacSha256Hex(data, props.getSecret());
            return computed.equals(razorpaySignature);
        } catch (Exception e) {
            return false;
        }
    }

    public void updatePaid(String razorpayOrderId, String razorpayPaymentId) {
        paymentRepository.markPaid(razorpayOrderId, razorpayPaymentId);
    }

    private static String hmacSha256Hex(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] digest = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
