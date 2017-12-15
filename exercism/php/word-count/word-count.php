<?php
function wordCount($sentence)
{
	$sentence = strtolower( $sentence );
//	$sentence = preg_filter( "/\s/", " ", $sentence);
	$sentence = preg_filter( "/[^a-z\d ]/", "", "!".$sentence);
    $words = preg_split("/ +/", $sentence);

//    echo var_dump($words);
    $unique = array();
    for ($i = 0; $i < sizeof($words); $i++)
        if (array_key_exists($words[$i], $unique))
            $unique[$words[$i]]++;
        else
	        $unique[ $words[ $i ] ] = 1;

    return $unique;
}