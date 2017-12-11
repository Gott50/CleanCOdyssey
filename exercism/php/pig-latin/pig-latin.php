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

    if (beginsWithVowel($english) || edgeCase($english)) {
        $start = $english;
        $end = "";
    } else {
        $start = substr($english, 1);
        $end = substr($english, 0,1);
    }

    if (beginsWithPair($english)) {
        $start = substr($english, 2);
        $end = substr($english, 0,2);
    }

    if (beginsWithTriple($english)) {
        $start = substr($english, 3);
        $end = substr($english, 0,3);
    }


    return $start . $end . "ay";
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