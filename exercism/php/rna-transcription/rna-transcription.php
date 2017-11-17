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
    if ($dna == 'G') return 'C';
    if ($dna == 'C') return 'G';
    if ($dna == 'T') return 'A';
    if ($dna == 'A') return 'U';
    return false;
}