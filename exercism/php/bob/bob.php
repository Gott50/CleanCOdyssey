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
        $character_mask = array('\n', '\r', ' ', '\t', '\u000b', '\u00a0', '\u2002');
        $trimmed = trim($string);
        if (preg_match("/[A-Z]+/", $trimmed)
            && $this->sameAsUpperCase($trimmed))
            return "Whoa, chill out!";
        if (preg_match("/\?\s*$/", $trimmed))
            return "Sure.";
        if (preg_match("/^\s*$/", $trimmed))
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