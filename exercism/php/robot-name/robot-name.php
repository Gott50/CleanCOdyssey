<?php

class Robot
{
    private $name;
    static $names = [];

    /**
     * Robot constructor.
     */
    public function __construct()
    {
        $this->reset();
    }

    /**
     * @return string
     */
    public function getName():string
    {
        return $this->name;
    }

    public function reset()
    {
        do {
            $this->name = chr(rand(65, 65 + 25)) . chr(rand(65, 65 + 25))
                . rand(0, 9) . rand(0, 9) . rand(0, 9);
        } while (in_array($this->name, Robot::$names));
        array_push(Robot::$names, $this->name);
    }
}