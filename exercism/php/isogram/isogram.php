<?php
function isIsogram($in){
    $in = strtolower($in);
    $containing = [];
    for ($i = 0; $i < strlen($in); $i++){
        if (in_array($in[$i],$containing))
            return false;
        array_push($containing,$in[$i]);
    }
    return true;
}