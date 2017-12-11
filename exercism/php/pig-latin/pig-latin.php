<?php
/**
 * @param $english
 * @return string
 */
function translate($english)
{
    $split = preg_split("/ /", $english);
    $map = array_map("translateWord", $split);

    return join(" ", $map);
}

/**
 * @param $english
 * @return string
 */
function translateWord($english): string
{
    if (beginsWithVowel($english) || edgeCase($english))
        list($start, $end) = split($english, 0);
    else
        list($start, $end) = split($english, 1);
    if (beginsWithPair($english))
        list($start, $end) = split($english, 2);
    if (beginsWithTriple($english))
        list($start, $end) = split($english, 3);

    return $start . $end . "ay";
}

/**
 * @param $english
 * @param $at
 * @return array
 */
function split($english, $at): array
{
    $start = substr($english, $at);
    $end = substr($english, 0, $at);
    return array($start, $end);
}

/**
 * @param $english
 * @return bool
 */
function edgeCase($english): bool
{
    $start = $english[0] . $english[1];
    return $start == "yt" || $start == "xr";
}

/**
 * @param $english
 * @return bool
 */
function beginsWithTriple($english): bool
{
    $start = $english[0] . $english[1] . $english[2];
    return $start == "squ" || $start == "thr" || $start == "sch";
}

/**
 * @param $english
 * @return bool
 */
function beginsWithPair($english): bool
{
    $start = $english[0] . $english[1];
    return $start == "ch" || $start == "qu" || $start == "th";
}

/**
 * @param $word
 * @return bool
 */
function beginsWithVowel($word): bool
{
    $start = $word[0];
    return $start == "a" || $start == "e" || $start == "i"
        || $start == "o" || $start == "u";
}