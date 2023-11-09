package com.calculator.backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pair<P1, P2> {

    P1 value1;
    P2 value2;
}
