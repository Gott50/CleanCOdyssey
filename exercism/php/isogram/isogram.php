<?php
function isIsogram($in){
    $in = strtolower($in);
    $in = preg_replace("/-/","",$in);
    $in = preg_replace("/\s/","",$in);

    $spec = preg_split("/\w+/", $in);
    $spec = array_filter($spec,function ($s) {return $s !== "";});
    if(sizeof($spec) !== sizeof(array_unique($spec))) return false;

    $in = preg_replace("/\W/","",$in);
    $containing = [];
    for ($i = 0; $i < strlen($in); $i++){
        $char = substr($in,$i,1);
        if (in_array($char,$containing)){
            return false;
        }
        array_push($containing,$in[$i]);
    }
    return true;
}