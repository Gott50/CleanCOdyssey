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
    if (startsWith($english, array("a", "e", "i", "o", "u"))
        || startsWith($english, array("yt", "xr")))
        list($start, $end) = split($english, 0);
    else
        list($start, $end) = split($english, 1);


    $starts = array("squ", "thr", "sch", "ch", "qu", "th");
    if (startsWith($english, $starts))
        list($start, $end) = split($english, startsWith($english, $starts));

    return $start . $end . "ay";
}

/**
 * @param $english
 * @param $at
 * @return array
 */
function split($english, $at): array
{
    return array(substr($english, $at),
        substr($english, 0, $at));
}

/**
 * @param $word
 * @param $with
 * @return int
 */
function startsWith($word, $with): int
{
    for ($i = 0; $i < sizeof($with); $i++)
        if (substr($word, 0, strlen($with[$i])) == $with[$i])
            return strlen($with[$i]);

    return 0;
}