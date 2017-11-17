<?php
function toRna($dna)
{
    $out = '';
    for ($i = 0; $i < strlen($dna); $i++)
        $out .= charToRna($dna[$i]);
    return $out;
}

/**
 * @param $dna
 * @return bool|string
 */
function charToRna($dna)
{
    return array('G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')[$dna];
}