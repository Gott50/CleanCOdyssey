<?php
function toRoman($int)
{
    $roman = array("I", "V", "X", "L", "C", "D", "M");
    list($out, $int) = loop($int, "", 1000, "M");
    for ($i = 4, $f = 100; $i >= 0; $i -= 2, $f /= 10)
        list($out, $int) = r2($int, $out, $roman, $i, $f);
    return $out;
}

/**
 * @param $int
 * @param $out
 * @param $roman
 * @param $index
 * @param $factor
 * @return array
 */
function r2($int, $out, $roman, $index, $factor): array
{
    $small = $roman[$index++];
    $big = $roman[$index++];
    $bigger = $roman[$index++];

    list($out, $int) = loop($int, $out, 9 * $factor, $small . $bigger);
    list($out, $int) = loop($int, $out, 6 * $factor, $big . $small);
    list($out, $int) = loop($int, $out, 5 * $factor, $big);
    list($out, $int) = loop($int, $out, 4 * $factor, $small . $big);
    list($out, $int) = loop($int, $out, 1 * $factor, $small);
    return array($out, $int);
}

/**
 * @param $int
 * @param $out
 * @param $q
 * @param $char
 * @return array
 */
function loop($int, $out, $q, $char): array
{
    for ($i = 1; $i <= $int / $q; $i++)
        $out .= $char;

    $int %= $q;
    return array($out, $int);
}