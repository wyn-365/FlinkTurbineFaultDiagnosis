/*
 * Copyright (c) 2018. Atguigu Inc. All Rights Reserved.
 */

package com.wang.turbine.config

import org.apache.commons.configuration2.{FileBasedConfiguration, PropertiesConfiguration}
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder
import org.apache.commons.configuration2.builder.fluent.Parameters

/**
  * 配置工具类
  */
object ConfigurationManager {

  // 创建用于初始化配置生成器实例的参数对象
  private val params = new Parameters()

  private val builder = new FileBasedConfigurationBuilder[FileBasedConfiguration](classOf[PropertiesConfiguration])
    .configure(params.properties().setFileName("turbine.properties"))

  // 通过getConfiguration获取配置对象
  val config = builder.getConfiguration()

}
