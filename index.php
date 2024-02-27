<?php

function calculateGrossPay($hourlyWage, $hoursWorked) {
    // Calculate normal hours and overtime hours
    $normalHours = min($hoursWorked, 40);
    $overtimeHours = max(0, $hoursWorked - 40);
    
    // Calculate overtime rate
    $overtimeRate = 1.5 * $hourlyWage;

    // Calculate gross pay
    return ($normalHours * $hourlyWage) + ($overtimeHours * $overtimeRate);
}


// Function to calculate estimated taxes
function calculateTaxes($grossPay, $taxRate) {
    // Calculate taxes based on the tax rate
    return $grossPay * ($taxRate / 100);
}


// Initialize variables
$grossPay = $taxes = $netPay = 0;

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get user input
    $hourlyWage = $_POST["hourlyWage"];
    $hoursWorked = $_POST["hoursWorked"];
    $taxRate = $_POST["taxRate"];

    // Calculate gross pay
    $grossPay = calculateGrossPay($hourlyWage, $hoursWorked);

    // Calculate estimated taxes
    $taxes = calculateTaxes($grossPay, $taxRate);

    // Calculate net pay
    $netPay = $grossPay - $taxes;
}
?>


<!DOCTYPE html>
<html>
<head>
    <title>Weekly Pay Calculator</title>
</head>
<body>

    <h2>Weekly Pay Calculator</h2>

    <?php if ($_SERVER["REQUEST_METHOD"] == "POST"): ?>
        <h2>Results</h2>
        <p>Your Gross Pay: $<?php echo number_format($grossPay, 2); ?></p>
        <p>Taxes Owed: $<?php echo number_format($taxes, 2); ?></p>
        <p>Your Net Pay: $<?php echo number_format($netPay, 2); ?></p>
    <?php else: ?>
        <p>Fill out the form below to calculate your weekly pay.</p>

        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
            <label for="hourlyWage">Hourly Wage:</label>
            <input type="number" name="hourlyWage" required step="0.01"><br>

            <label for="hoursWorked">Hours Worked:</label>
            <input type="number" name="hoursWorked" required step="0.01"><br>

            <label for="taxRate">Estimated Tax Rate:</label>
            <input type="number" name="taxRate" required step="0.01"><br>

            <input type="submit" value="Calculate">
        </form>
    <?php endif; ?>

</body>
</html>
