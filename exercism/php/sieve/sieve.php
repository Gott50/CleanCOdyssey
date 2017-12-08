<?php
function sieve($int)
{
    if ($int <= 1) return array();

    $primes = array();
    $unmarked = range(2, $int);

    while (sizeof($unmarked)) {
        $prime = array_shift($unmarked);
        array_push($primes, $prime);
        $unmarked = filter($unmarked, $prime);
    }

    return $primes;
}

function filter($unmarked, $prime)
{
    $out = array();
    for ($i = 0; $i < sizeof($unmarked); $i++)
        if ($unmarked[$i] % $prime)
            array_push($out, $unmarked[$i]);

    return $out;
}