<?php
function wordCount($sentence)
{
    $words = preg_split("/ +/", $sentence);

    $unique = array();
    for ($i = 0; $i < sizeof($words); $i++)
        if (array_key_exists($words[$i], $unique))
            $unique[$words[$i]]++;
        else
            $unique = array_merge($unique, array($words[$i] => 1));

//    echo var_dump($unique);
    return $unique;
}