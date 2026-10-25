/*
 * MIT License
 *
 * Copyright (c) 2026 Len (田隆)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.godikit.enums;

import java.util.Objects;

/**
 * A boolean state that maps an arbitrary business value onto {@link BooleanState#TRUE} or
 * {@link BooleanState#FALSE}.
 *
 * <p>Real-world boolean fields are rarely stored as native booleans. They use conventions such as
 * {@code "Y"}/{@code "N"}, {@code 1}/{@code 0}, or {@code "true"}/{@code "false"}, and a value that
 * matches none of the recognized references (such as {@code null}) has no natural boolean meaning.
 * This enum carries the mapping rules so that the same conventions can be applied consistently
 * across a codebase.</p>
 *
 * <p>Use {@link #from(Object, Object, Object)} to convert a business value into a state, and
 * {@link #toValue(Object, Object, Object)} to convert a state back into a business value.</p>
 *
 * <p>This enum is stateless and thread-safe.</p>
 */
public enum BooleanState {
    /**
     * The business value matches the reference value that represents {@code true}.
     */
    TRUE,
    /**
     * The business value matches the reference value that represents {@code false}.
     */
    FALSE;

    /**
     * Maps a business value onto a boolean state using explicit reference values.
     *
     * <p>The value is compared with {@link Objects#equals(Object, Object)}, so {@code null} is
     * supported both as the value under evaluation and as a reference value.</p>
     *
     * @param valueOfCompare the business value to evaluate, may be {@code null}
     * @param compareTrue    the reference value that represents {@link BooleanState#TRUE}, may be {@code null}
     * @param compareFalse   the reference value that represents {@link BooleanState#FALSE}, may be {@code null}
     * @param noTrueNoFalse  the state returned when the value matches neither reference value
     * @param <T>            the type of the value and the reference values
     * @return {@link BooleanState#TRUE} if the value matches {@code compareTrue},
     * {@link BooleanState#FALSE} if it matches {@code compareFalse},
     * otherwise {@code noTrueNoFalse}
     */
    public static <T> BooleanState from(final T valueOfCompare, final T compareTrue, final T compareFalse, final BooleanState noTrueNoFalse) {
        return Objects.equals(valueOfCompare, compareTrue) ? TRUE : (Objects.equals(valueOfCompare, compareFalse) ? FALSE : noTrueNoFalse);
    }

    /**
     * Maps a business value onto a boolean state using explicit reference values.
     *
     * <p>When the value matches neither reference value, {@link BooleanState#FALSE} is returned.
     * Use {@link #from(Object, Object, Object, BooleanState)} when an unmatched value must be
     * handled differently.</p>
     *
     * @param valueOfCompare the business value to evaluate, may be {@code null}
     * @param compareTrue    the reference value that represents {@link BooleanState#TRUE}, may be {@code null}
     * @param compareFalse   the reference value that represents {@link BooleanState#FALSE}, may be {@code null}
     * @param <T>            the type of the value and the reference values
     * @return {@link BooleanState#TRUE} if the value matches {@code compareTrue},
     * {@link BooleanState#FALSE} otherwise
     */
    public static <T> BooleanState from(final T valueOfCompare, final T compareTrue, final T compareFalse) {
        return from(valueOfCompare, compareTrue, compareFalse, FALSE);
    }

    /**
     * Maps this state back onto a business value.
     *
     * @param valueTrue          the value returned for {@link BooleanState#TRUE}
     * @param valueFalse         the value returned for {@link BooleanState#FALSE}
     * @param valueNoTrueNoFalse the value returned when this state is neither {@link BooleanState#TRUE}
     *                           nor {@link BooleanState#FALSE}
     * @param <T>                the type of the mapped values
     * @return the value matching the current state
     */
    public <T> T toValue(final T valueTrue, final T valueFalse, final T valueNoTrueNoFalse) {
        return this == TRUE ? valueTrue : (this == FALSE ? valueFalse : valueNoTrueNoFalse);
    }

    /**
     * Maps this state back onto a business value.
     *
     * <p>{@code valueFalse} is also returned when this state is neither {@link BooleanState#TRUE}
     * nor {@link BooleanState#FALSE}.</p>
     *
     * @param valueTrue  the value returned for {@link BooleanState#TRUE}
     * @param valueFalse the value returned for {@link BooleanState#FALSE}, and for any state that is
     *                   neither {@link BooleanState#TRUE} nor {@link BooleanState#FALSE}
     * @param <T>        the type of the mapped values
     * @return the value matching the current state
     */
    public <T> T toValue(final T valueTrue, final T valueFalse) {
        return toValue(valueTrue, valueFalse, valueFalse);
    }
}
