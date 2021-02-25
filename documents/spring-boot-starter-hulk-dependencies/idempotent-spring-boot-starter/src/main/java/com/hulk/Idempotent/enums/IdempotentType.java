
package com.hulk.Idempotent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *  幂等类型
 * </p>
 *
 * @author hulk
 * @since 2019-03-01
 */
@AllArgsConstructor
@Getter
public enum IdempotentType {

    IDEMPOTENT_HEAD("head"),

    IDEMPOTENT_FROM("from");


    private final String type;


    public static IdempotentType getIdempotentType(String type) {
        IdempotentType[] dts = IdempotentType.values();
        for (IdempotentType dt : dts) {
            if (dt.getType().equalsIgnoreCase(type)) {
                return dt;
            }
        }
        return IDEMPOTENT_HEAD;
    }
}
