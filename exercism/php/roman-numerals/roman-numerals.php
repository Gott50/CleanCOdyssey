<?php
function toRoman($int)
{
    $out ="";


    list($out, $int) = l($int, $out, 1000, "M");

    list($out, $int) = r($int, $out, 100, "C", "D", "M");
    list($out, $int) = r($int, $out, 10, "X", "L", "C");
    list($out, $int) = r($int, $out, 1, "I", "V", "X");
    return $out;
}

/**
 * @param $int
 * @param $out
 * @param $factor
 * @param $small
 * @param $bigger
 * @param $big
 * @return mixed
 */
function r($int, $out, $factor, $small, $big, $bigger)
{
    list($out, $int) = l($int, $out, 9 * $factor, $small . $bigger);
    list($out, $int) = l($int, $out, 6 * $factor, $big . $small);
    list($out, $int) = l($int, $out, 5 * $factor, $big);
    list($out, $int) = l($int, $out, 4 * $factor, $small . $big);
    list($out, $int) = l($int, $out, 1 * $factor, $small);
    return array($out, $int);
}

/**
 * @param $int
 * @param $out
 * @param $q
 * @param $char
 * @return array
 */
function l($int, $out, $q, $char): array
{
    $out .= loop($int / $q, $char);
    $int %= $q;
    return array($out, $int);
}

/**
 * @param $times
 * @param $char
 * @return string
 */
function loop($times, $char): string
{
    $out = "";
    for ($i = 1; $i <= $times; $i++) {
        $out .= $char;
    }
    return $out;
}