<?php

function isPangram($string)
{
    foreach (range('a', 'z') as $l)
        if (!strpos(" " . strtolower($string), $l))
            return false;

    return true;
}