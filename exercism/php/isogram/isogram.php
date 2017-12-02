<?php
function isIsogram($in){
    $in = strtolower($in);
    $in = preg_replace("/-/","",$in);
    $in = preg_replace("/\s/","",$in);

    $spec = preg_split("/\w+/", $in);
    $spec = array_filter($spec,function ($s) {return $s !== "";});
    if(sizeof($spec) !== sizeof(array_unique($spec))) return false;

    $in = preg_replace("/\W/","",$in);
    $chars =  str_split($in);

    if(sizeof($chars) !== sizeof(array_unique($chars))) return false;

    return true;
}