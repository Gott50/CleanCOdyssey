<?php
function isIsogram($in){
    $in = strtolower($in);
    $in = preg_replace("/-/","",$in);

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