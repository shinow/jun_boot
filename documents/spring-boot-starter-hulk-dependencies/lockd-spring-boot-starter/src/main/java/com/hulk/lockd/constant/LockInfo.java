package com.hulk.lockd.constant;

import com.hulk.lockd.enums.LockType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author  hulk
 * 锁基本信息
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LockInfo {

    private LockType type;
    private String name;
    private long acquireTimeout;
    private long expire;




}
