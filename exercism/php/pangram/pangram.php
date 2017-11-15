<?php

function isPangram($string){
    if (strlen($string) < 26) return false;

    $letters = range('a', 'z');
    foreach ($letters as $l) {
        if(!strpos(" ".$string,$l))
        return false;
    }
    return true;
}