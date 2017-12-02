<?php
function isIsogram($in)
{
    $in = strtolower($in);
    $in = preg_replace("/-/", "", $in);
    $in = preg_replace("/\s/", "", $in);
    $spec = array_filter(preg_split("/\w+/", $in), function ($s) {
        return $s !== "";
    });
    $in = preg_replace("/\W/", "", $in);

    return !(sizeof($spec) !== sizeof(array_unique($spec))) &&
        !(sizeof(str_split($in)) !== sizeof(array_unique(str_split($in))));
}