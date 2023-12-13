package com.dmslob.java17.sealed.base;

// Climber is not allowed from another package;

import java.io.Serializable;

public sealed abstract class Plant implements Serializable permits Herb, Shrub, Tree {
}
