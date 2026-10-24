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
package com.godikit.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility helpers for {@link String} emptiness checks and null-safe defaults.
 *
 * <p>This class is stateless and thread-safe.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    /**
     * Returns the given string when it is not {@code null}, otherwise the fallback value.
     *
     * @param s   the string to evaluate, may be {@code null}
     * @param def the fallback value returned when {@code s} is {@code null}
     * @return {@code s} if it is not {@code null}, otherwise {@code def}
     */
    public static String defaultIfNull(String s, String def) {
        return ObjectUtils.defaultIfNull(s, def);
    }

    /**
     * Returns the given string when it is neither {@code null} nor empty, otherwise the fallback value.
     *
     * @param s   the string to evaluate, may be {@code null}
     * @param def the fallback value returned when {@code s} is {@code null} or empty
     * @return {@code s} if it is not empty, otherwise {@code def}
     */
    public static String defaultIfEmpty(String s, String def) {
        return isEmpty(s) ? def : s;
    }

    /**
     * Returns the given string when it is neither {@code null} nor blank, otherwise the fallback value.
     *
     * <p>A blank string is one whose trimmed content is empty, e.g. {@code "  "}.</p>
     *
     * @param s   the string to evaluate, may be {@code null}
     * @param def the fallback value returned when {@code s} is {@code null} or blank
     * @return {@code s} if it is not blank, otherwise {@code def}
     */
    public static String defaultIfBlank(String s, String def) {
        return isBlank(s) ? def : s;
    }

    /**
     * Checks whether the given string is {@code null} or has zero length.
     *
     * @param s the string to evaluate, may be {@code null}
     * @return {@code true} if {@code s} is {@code null} or empty, otherwise {@code false}
     */
    public static boolean isEmpty(String s) {
        return ObjectUtils.isNull(s) || s.isEmpty();
    }

    /**
     * Checks whether the given string is neither {@code null} nor empty.
     *
     * @param s the string to evaluate, may be {@code null}
     * @return {@code true} if {@code s} is not empty, otherwise {@code false}
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Checks whether the given string is {@code null} or contains only whitespace.
     *
     * @param s the string to evaluate, may be {@code null}
     * @return {@code true} if {@code s} is {@code null} or blank, otherwise {@code false}
     */
    public static boolean isBlank(String s) {
        return ObjectUtils.isNull(s) || s.trim().isEmpty();
    }

    /**
     * Checks whether the given string is neither {@code null} nor blank.
     *
     * @param s the string to evaluate, may be {@code null}
     * @return {@code true} if {@code s} is not blank, otherwise {@code false}
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
