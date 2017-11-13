<?php

class Bob
{

    /**
     * Bob constructor.
     */
    public function __construct()
    {
    }

    public function respondTo($string)
    {
        if (preg_match("/[A-Z]+/", $string)
            && $this->sameAsUpperCase($string))
            return "Whoa, chill out!";
        if (preg_match("/\?\s*$/", $string))
            return "Sure.";
        if (preg_match("/^\s*$/", $string))
            return "Fine. Be that way!";
        return "Whatever.";
    }

    /**
     * @param $string
     * @return int
     */
    public function sameAsUpperCase($string): int
    {
        return strtoupper($string)==$string;
    }
}