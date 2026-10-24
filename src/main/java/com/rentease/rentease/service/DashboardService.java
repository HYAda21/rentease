package com.rentease.rentease.service;

import com.rentease.rentease.entity.*;
import com.rentease.rentease.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;
    private final LeaseReposiitory leaseRepository;
    private final PaymentRepository paymentRepository;
    private final SupportRepository supportRepository;

    public DashboardService(
            UserRepository userRepository,
            PropertyRepository propertyRepository,
            RoomRepository roomRepository,
            LeaseReposiitory leaseRepository,
            PaymentRepository paymentRepository,
            SupportRepository supportRepository) {

        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.leaseRepository = leaseRepository;
        this.paymentRepository = paymentRepository;
        this.supportRepository = supportRepository;
    }

    // ================= OWNER DASHBOARD =================

    public Object getOwnerDashboard(Long ownerId) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        Map<String, Object> dashboard = new HashMap<>();

        // Owner name
        dashboard.put("ownerName", owner.getName());

        // Properties
        List<Property> properties =
                propertyRepository.findByOwnerId(ownerId);

        dashboard.put("totalProperties", properties.size());

        // Rooms
        List<Room> rooms =
                roomRepository.findByPropertyOwnerId(ownerId);

        dashboard.put("totalRooms", rooms.size());

        long occupiedRooms = rooms.stream()
                .filter(room ->
                        room.getRoomStatus() == RoomStatus.Occupid)
                .count();

        long availableRooms = rooms.stream()
                .filter(room ->
                        room.getRoomStatus() == RoomStatus.Available)
                .count();

        dashboard.put("occupiedRooms", occupiedRooms);
        dashboard.put("availableRooms", availableRooms);

        // Leases
        List<Lease> leases =
                leaseRepository.findByRoomPropertyOwnerId(ownerId);

        dashboard.put("totalLeases", leases.size());

        long activeLeases = leases.stream()
                .filter(lease ->
                        lease.getLeaseStatus() == LeaseStatus.Active)
                .count();

        dashboard.put("activeLeases", activeLeases);

        // Payments
        List<Payment> payments =
                paymentRepository.findByLeaseRoomPropertyOwnerId(ownerId);

        long pendingPayments = payments.stream()
                .filter(payment ->
                        payment.getPaymentStatus() == PaymentStatus.PENDING)
                .count();

        dashboard.put("totalPayments", payments.size());
        dashboard.put("pendingPayments", pendingPayments);

        // Complaints
        List<Support> supports =
                supportRepository.findByRoomPropertyOwnerId(ownerId);

        long openComplaints = supports.stream()
                .filter(support ->
                        support.getStatus() == SupportStatus.OPEN)
                .count();

        dashboard.put("totalComplaints", supports.size());
        dashboard.put("openComplaints", openComplaints);

        return dashboard;
    }

    // ================= TENANT DASHBOARD =================

    public Object getTenantDashboard(Long tenantId) {

        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        Map<String, Object> dashboard = new HashMap<>();

        // Tenant name
        dashboard.put("tenantName", tenant.getName());

        // My leases
        List<Lease> leases =
                leaseRepository.findByTenantId(tenantId);

        dashboard.put("totalLeases", leases.size());

        long activeLeases = leases.stream()
                .filter(lease ->
                        lease.getLeaseStatus() == LeaseStatus.Active)
                .count();

        dashboard.put("activeLeases", activeLeases);

        // My payments
        List<Payment> payments =
                paymentRepository.findByLeaseTenantId(tenantId);

        dashboard.put("totalPayments", payments.size());

        long pendingPayments = payments.stream()
                .filter(payment ->
                        payment.getPaymentStatus() == PaymentStatus.PENDING)
                .count();

        dashboard.put("pendingPayments", pendingPayments);

        // My complaints
        List<Support> supports =
                supportRepository.findByTenantId(tenantId);

        dashboard.put("totalComplaints", supports.size());

        long openComplaints = supports.stream()
                .filter(support ->
                        support.getStatus() == SupportStatus.OPEN)
                .count();

        dashboard.put("openComplaints", openComplaints);

        // Current room
        if (!leases.isEmpty()) {

            Lease activeLease = leases.stream()
                    .filter(lease ->
                            lease.getLeaseStatus() == LeaseStatus.Active)
                    .findFirst()
                    .orElse(leases.get(0));

            Room room = activeLease.getRoom();

            if (room != null) {
                dashboard.put("roomNumber", room.getRoomNumber());
                dashboard.put("roomType", room.getRoomType());
                dashboard.put("rent", room.getRent());
            }
        }

        return dashboard;
    }
}
