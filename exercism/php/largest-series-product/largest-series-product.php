<?php

class Series
{
    /**
     * @var string
     */
    private $string;

    /**
     * Series constructor.
     * @param string $string
     */
    public function __construct($string)
    {
        $this->string = $string;
    }

    /**
     * @param $int
     * @return int
     */
    public function largestProduct($int)
    {
        if (!$this->isValid($int)) throw new InvalidArgumentException;

        $out = 0;
        for ($i = 0; $i <= strlen($this->string) - $int; $i++) {
            $productOfSeries = $this->productOfSeries(substr($this->string, $i, $int));
            $out = $out < $productOfSeries ? $productOfSeries : $out;
        }
        return $out;
    }

    /**
     * @param $string
     * @return int
     */
    private function productOfSeries($string): int
    {
        $out = 1;
        for ($i = 0; $i < strlen($string); $i++)
            $out *= intval($string[$i]);
        return $out;
    }

    /**
     * @param $int
     * @return bool
     */
    private function isValid($int): bool
    {
        return strlen($this->string) >= $int  && $int >= 0
            && preg_match("/^\d*$/", $this->string);
    }

}