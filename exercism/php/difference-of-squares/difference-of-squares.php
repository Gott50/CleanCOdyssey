<?php
function squareOfSums($in){
    $out = 0;
    for($i = 1; $i <= $in; $i++)
        $out += $i;
    return $out*$out;
}

function sumOfSquares($in){
    $out = 0;
    for($i = 1; $i <= $in; $i++)
        $out += $i*$i;
    return $out;
}

function difference($in){
    return squareOfSums($in) - sumOfSquares($in);
}