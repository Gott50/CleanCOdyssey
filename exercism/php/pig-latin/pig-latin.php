<?php
function translate($english)
{
    if (beginsWithVowel($english)) $out = $english;
    else if (beginsWithPair($english)) $out = substr($english, 2) . $english[0]. $english[1];
    else $out = substr($english, 1).  $english[0];

    return $out . "ay";
}

/**
 * @param $english
 * @return bool
 */
function beginsWithPair($english): bool
{
    $start = $english[0] . $english[1];
    return $start == "ch"||$start == "qu";
}

/**
 * @param $word
 * @return bool
 */
function beginsWithVowel($word) :bool
{
    return $word[0] == "a" || $word[0] == "e" || $word[0] == "i" || $word[0] == "o"|| $word[0] == "u";
}