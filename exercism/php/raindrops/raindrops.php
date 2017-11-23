<?php
function raindrops($d)
{
    $out = "";
    if ($d % 3 == 0)
        $out .= "Pling";
    if ($d % 5 == 0)
        $out .= "Plang";
    if ($d % 7 == 0)
        $out .= "Plong";

    return strlen($out) ? $out : $d . "";
}