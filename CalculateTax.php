<?php

function calculateTax($income, $deductions) {
    // Standard deduction for individuals
    $standardDeduction = 12950;

    // Use standard deduction if deductions are less than the standard deduction
    $adjustedGrossIncome = max($income - $deductions, $standardDeduction);

    // Tax brackets
    $brackets = [
        11000 => 0.10,
        44725 => 0.12,
        95375 => 0.22,
        182100 => 0.24,
        231250 => 0.32,
        578125 => 0.35,
        PHP_INT_MAX => 0.37
    ];

    // Initialize variables
    $totalTaxes = 0;
    $taxesByBracket = [];
    
    
    // Display name, gross income, and total deductions
    echo "Tax Calculator Results for " . htmlspecialchars($_POST['name']) . "<br>";
    echo "Gross Income : $" . number_format($income, 2) . "<br>";
    echo "Total Deductions : $" . number_format($deductions, 2) . "<br>";
    
    // Calculate taxes for each bracket
    $previousBracket = 0;
    foreach ($brackets as $limit => $rate) {
        // Calculate taxable income for the current bracket
        $taxableIncome = max(0, min($income - $previousBracket, $limit - $previousBracket));

        // Calculate taxes for the current bracket
        $taxesByBracket[$rate] = $taxableIncome * $rate;

        // Update total taxes
        $totalTaxes += $taxesByBracket[$rate];

        // Display taxes for the current bracket
        echo "Taxes Owed at " . ($rate * 100) . "% bracket : $" . number_format($taxesByBracket[$rate], 2) . "<br>";

        // Update the previous bracket for the next iteration
        $previousBracket = $limit;
    }

    // Display zero values for unused brackets
    foreach ($brackets as $rate) {
        if (!isset($taxesByBracket[$rate])) {
            echo "Taxes Owed at " . ($rate * 100) . "% bracket : $0.00<br>";
        }
    }

    // Display results
    echo "Adjusted Gross Income : $" . number_format($adjustedGrossIncome, 2) . "<br>";
    echo "Total Taxes Owed : $" . number_format($totalTaxes, 2) . "<br>";
    echo "Taxes Owed as percentage of gross income: " . number_format(($totalTaxes / $income) * 100, 2) . "%<br>";
    echo "Taxes Owed as percentage of adjusted gross income: " . number_format(($totalTaxes / $adjustedGrossIncome) * 100, 2) . "%<br>";
}

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['income']) && isset($_POST['deductions'])) {
    // Validate input
    $name = htmlspecialchars($_POST['name']);
    $income = is_numeric($_POST['income']) ? $_POST['income'] : 0;
    $deductions = is_numeric($_POST['deductions']) ? $_POST['deductions'] : 0;

    // Calculate taxes
    calculateTax($income, $deductions);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tax Calculator</title>
</head>
<body>
    <form method="post" action="">
        <label for="name">Name:</label>
        <input type="text" name="name" required><br>

        <label for="income">Gross Income:</label>
        <input type="text" name="income" required><br>

        <label for="deductions">Total Deductions:</label>
        <input type="text" name="deductions" required><br>

        <input type="submit" value="Calculate Taxes">
    </form>
</body>
</html>
