<?php

class Bob
{

    public function respondTo($string)
    {
        $cleaned = str_replace(array('\n', '\r', '\t', '\u000b', '\u00a0', '\u2002'),
            '', $string);
        if (preg_match("/[A-Z]+/", $cleaned)
            && $this->sameAsUpperCase($cleaned))
            return "Whoa, chill out!";
        if (preg_match("/\?\s*$/", $cleaned))
            return "Sure.";
        if (preg_match("/^\s*$/", $cleaned))
            return "Fine. Be that way!";
        return "Whatever.";
    }

    private function sameAsUpperCase($string): bool
    {
        return mb_strtoupper($string, 'UTF-8') == $string;
    }
}