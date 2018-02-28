<?php

class School {
	private $grades = [
		1 => [],
		2 => [],
		3 => [],
		4 => [],
		5 => [],
		6 => [],
	];

	function numberOfStudents() {
		return array_reduce( $this->grades, function ( $acc, $g ) {
			return $acc + sizeof( $g );
		}, 0 );
	}

	function add( $name, $grade ) {
		if ( array_key_exists( $grade, $this->grades ) ) {
			array_push( $this->grades[ $grade ], $name );
		}
	}

	function grade( $grade ) {
		return $this->grades[ $grade ];
	}

	function studentsByGradeAlphabetical() {
		foreach ( $this->grades as &$grade ) {
			if ( sizeof( $grade ) > 0 ) {
				sort( $grade );
			} else {
				unset( $grade );
			}
		}

		return array_filter( $this->grades, function ( $g ) {
			return sizeof( $g );
		} );
	}
}