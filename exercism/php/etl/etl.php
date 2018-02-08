<?php

function transform( $old ) {
	$out = [];
	foreach ( $old as $key => $value) {
		foreach ( $value as $item ) {
			$out[ strtolower( $item ) ] = $key;
		}
	}

	return $out;
}
