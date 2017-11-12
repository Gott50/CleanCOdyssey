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
        if (preg_match("/\?$/", $string))
            return "Sure.";
        else if (preg_match("/!$/", $string) || preg_match("/[A-Z]$/", $string))
            return "Whoa, chill out!";
        return "Whatever.";
    }
}