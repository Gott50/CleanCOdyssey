<?php
function isLeap($year)
{
    return $year % 4 == 0 && ($year % 100 || $year % 400 == 0);
}