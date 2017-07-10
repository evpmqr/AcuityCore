package com.acuity.api.rs.interfaces;

import com.acuity.api.rs.utils.direct_input.ScreenTarget;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public interface Clickable {

    Supplier<Optional<ScreenTarget>> EMPTY_SUPPLIER = Optional::empty;

    Supplier<Optional<ScreenTarget>> getScreenTargetSupplier();

}
