<?php
function from($date)
{
    return (new DateTime($date->format("Y-m-d H:i:s"), new DateTimeZone("UTC")))
        ->add(new DateInterval('PT1000000000S'));
}