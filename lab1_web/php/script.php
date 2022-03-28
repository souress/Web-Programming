<?php
date_default_timezone_set('Europe/Moscow');

$start = microtime(true);
$time = date("H:i:s");

$x_array_string = (string)$_POST["X"];
$x_array = preg_split("/; /", $x_array_string);
$x_array_checked = checkX($x_array);
$y = (float)$_POST["Y"];
$r = (int)$_POST["R"];
$rows = [];
$rows_json = [];

function checkX($x_array)
{
    $possibleX = array("-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3");
    $x_array_checked = [];
    foreach ($x_array as $x) {
        if (in_array($x, $possibleX))
            array_push($x_array_checked, intval($x));
    }
    return $x_array_checked;
}

function checkY($y)
{
    if (!is_numeric($y) | $y > 3 | $y < -5)
        return false;
    else
        return true;
}

function checkR($r)
{
    $possibleR = array("1", "2", "3", "4", "5");
    if (!in_array($r, $possibleR))
        return false;
    else
        return true;
}

function checkHit($x, $y, $r)
{
    if (($x >= 0) && ($x <= $r) && ($y <= ($r / 2)) && ($y >= 0))
        return true;
    elseif (($x >= $y) && ($x <= $r) && ($x >= 0))
        return true;
    elseif (($x <= 0) && ($y <= 0) && ($y >= -$r) && ($x >= -$r) && ($x * $x + $y * $y <= $r * $r))
        return true;
    else
        return false;
}

if (!empty($x_array_checked) && checkY($y) && checkR($r)) {
    $i = 0;
    foreach ($x_array_checked as $x_checked) {
        if (checkHit((int)$x_checked, $y, $r))
            $answer = "Есть";
        else
            $answer = "Нет";
        $end = number_format(microtime(true) - $start, 9, '.', '');
        $resultStr = "{\"x\" : {$x_checked}, \"y\" : {$y}, \"r\" : {$r}, \"time\" : \"{$time}\", \"end\" : {$end}, \"result\" : \"{$answer}\"}";
        array_push($rows_json, $resultStr);
    }
    $result = "[".implode(", ", $rows_json)."]";
    echo $result;
} else {
    echo "\nПараметры не прошли проверку!\n";
    if (empty($x_array_checked)) echo "\tПроверьте введенное значение X\n";
    if (!checkY($y)) echo "\tПроверьте введенное значение Y\n";
    if (!checkR($r)) echo "\tПроверьте введенное значение R\n";
}
