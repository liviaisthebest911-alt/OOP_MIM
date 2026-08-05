package Claude.bai2;

class EmployeeFactory {

    public static Employee create(String type, Object... params) {
        // switch expression — Java 14+
        return switch (type.toUpperCase()) {

            case "FULLTIME" -> new FullTimeEmployee(
                    (String) params[0],   // id
                    (String) params[1],   // name
                    (double) params[2]    // monthlySalary
            );

            case "PARTTIME" -> new PartTimeEmployee(
                    (String) params[0],   // id
                    (String) params[1],   // name
                    (double) params[2],   // hoursWorked
                    (double) params[3]    // hourlyRate
            );

            case "CONTRACTOR" -> new Contractor(
                    (String) params[0],   // id
                    (String) params[1],   // name
                    (double) params[2]    // projectFee
            );

            // Thêm loại mới → thêm case ở đây, KHÔNG sửa gì khác (OCP)
            default -> throw new IllegalArgumentException(
                    "Type khong hop le: " + type
            );
        };
    }
}