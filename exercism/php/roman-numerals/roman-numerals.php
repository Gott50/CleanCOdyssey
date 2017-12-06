<?php
function toRoman($int)
{
    $out ="";

    $roman = array("I","V","X","L","C","D","M");
    list($out, $int) = loop($int, $out, 1000, "M");

    list($out, $int) = r2($int, $out, $roman, 4, 100);
    list($out, $int) = r2($int, $out, $roman, 2, 10);
    list($out, $int) = r2($int, $out, $roman, 0, 1);
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