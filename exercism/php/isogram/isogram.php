<?php
function isIsogram($in)
{
    $in = strtolower($in);
    $in = preg_replace("/-/", "", $in);
    $in = preg_replace("/\s/", "", $in);

    $spec = preg_split("/\w+/", $in);
    $spec = array_filter($spec, function ($s) {
        return $s !== "";
    });

    $in = preg_replace("/\W/", "", $in);
    $chars = str_split($in);

    return !(sizeof($spec) !== sizeof(array_unique($spec))) &&
        !(sizeof($chars) !== sizeof(array_unique($chars)));
}