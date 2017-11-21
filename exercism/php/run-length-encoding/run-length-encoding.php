<?php

//
// This is only a SKELETON file for the "Run Length Encoding" exercise. It's been provided as a
// convenience to get you started writing code faster.
//

function encode($input)
{
    $pairs = [];
    for ($i = 0; $i < strlen($input); $i++){
        $lastChar = array_pop($pairs);
        if ($lastChar == null)
            array_push($pairs,1,$input[$i]);
        else {
            $lastCount = array_pop($pairs);
            if ($lastCount == null)
                array_push($pairs,1, $input[$i]);
            if ($lastChar == $input[$i])
                array_push($pairs,$lastCount+1, $input[$i]);
            else
                array_push($pairs,$lastCount,$lastChar,1, $input[$i]);
        }
    }
    return implode('',array_filter($pairs, "filter_1"));
}

function filter_1($value){
    return $value != 1;
}

function decode($input)
{
    $out = "";
    for($i = 0; $i < strlen($input); $i++){
        $times = 1;
        while (intval($input[$i])> 0){
            $times = intval($input[$i]);
            $i++;
        }
        for ($t = 0; $t < $times; $t++){
            $out .= $input[$i];
        }
    }
    return $out;
}
