<?php

class Allergies {
	private $score;

	public function __construct($score) {
		$this->score = $score;
	}

	public function isAllergicTo( $allergen ) {
		return ($this->score & $allergen->getScore()) != 0;
	}

	public function getList() {
		$out = [];
		foreach ( Allergen::allergenList() as $a ) {
			if ( $this->isAllergicTo( $a ) ) {
				array_push( $out, $a );
			}
		}

		return $out;
	}
}

class Allergen{
	const EGGS = 1;
	const PEANUTS = 2;
	const SHELLFISH = 4;
	const STRAWBERRIES = 8;
	const TOMATOES = 16;
	const CHOCOLATE = 32;
	const POLLEN = 64;
	const CATS = 128;

	private $score;

	public function __construct($score) {
		$this->score = $score;
	}

	public static function allergenList() {
		$out = [];
		for ( $i = 1; $i <= 128; $i *= 2 ) {
			array_push( $out, new Allergen( $i ) );
		}

		return $out;
	}

	public function getScore() {
		return $this->score;
	}
}