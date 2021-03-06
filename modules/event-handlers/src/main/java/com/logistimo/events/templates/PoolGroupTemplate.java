/*
 * Copyright © 2017 Logistimo.
 *
 * This file is part of Logistimo.
 *
 * Logistimo software is a mobile & web platform for supply chain management and remote temperature monitoring in
 * low-resource settings, made available under the terms of the GNU Affero General Public License (AGPL).
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * You can be released from the requirements of the license by purchasing a commercial license. To know more about
 * the commercial license, please contact us at opensource@logistimo.com
 */

package com.logistimo.events.templates;

import com.logistimo.config.models.EventsConfig;
import com.logistimo.entities.entity.IPoolGroup;
import com.logistimo.utils.LocalDateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by charan on 10/03/17.
 */
public class PoolGroupTemplate implements ITemplate {
  private final IPoolGroup poolGroup;

  public PoolGroupTemplate(IPoolGroup poolGroup) {
    this.poolGroup = poolGroup;
  }

  @Override
  public Map<String, String> getTemplateValues(Locale locale, String timezone,
                                               List<String> excludeVars, Date updationTime) {
    HashMap<String, String> varMap = new HashMap<String, String>();
    if (excludeVars == null || !excludeVars.contains(EventsConfig.VAR_CREATIONTIME)) {
      varMap.put(EventsConfig.VAR_CREATIONTIME,
          LocalDateUtil.format(poolGroup.getTimeStamp(), locale, timezone));
    }
    if (excludeVars == null || !excludeVars.contains(EventsConfig.VAR_ENTITYGROUP)) {
      varMap.put(EventsConfig.VAR_ENTITYGROUP, poolGroup.getName());
    }
    if (updationTime != null && (excludeVars == null || !excludeVars
        .contains(EventsConfig.VAR_UPDATIONTIME))) {
      varMap
          .put(EventsConfig.VAR_UPDATIONTIME, LocalDateUtil.format(updationTime, locale, timezone));
    }
    return varMap;
  }
}
