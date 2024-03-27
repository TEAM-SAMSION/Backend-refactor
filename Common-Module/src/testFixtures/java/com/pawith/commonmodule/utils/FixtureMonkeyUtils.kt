package com.pawith.commonmodule.utils

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.FixtureMonkeyBuilder
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector
import com.navercorp.fixturemonkey.api.jqwik.JavaTypeArbitraryGenerator
import com.navercorp.fixturemonkey.api.jqwik.JqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import net.jqwik.api.Arbitraries
import net.jqwik.api.arbitraries.IntegerArbitrary
import net.jqwik.api.arbitraries.LongArbitrary
import net.jqwik.api.arbitraries.StringArbitrary

class FixtureMonkeyUtils private constructor() {
    companion object {
        fun getKotlinBasedFixtureMonkey(): FixtureMonkey {
            return FixtureMonkey.builder()
                .defaultNotNull(true)
                .plugin(KotlinPlugin())
                .build()
        }

        private val CONSTRUCT_BASED_FIXTURE_MONKEY: FixtureMonkey = setUpJavaType(FixtureMonkeyBuilder())
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .defaultNotNull(true)
            .build();

        private val BUILDER_BASED_FIXTURE_MONKEY: FixtureMonkey = setUpJavaType(FixtureMonkey.builder())
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .defaultNotNull(true)
            .build()

        private val REFLECTION_BASED_FIXTURE_MONKEY: FixtureMonkey = setUpJavaType(FixtureMonkey.builder())
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .defaultNotNull(true)
            .build()

        private val JAVA_TYPE_BASED_FIXTURE_MONKEY: FixtureMonkey = setUpJavaType(FixtureMonkey.builder())
            .defaultNotNull(true)
            .build()

        @JvmStatic
        fun getConstructBasedFixtureMonkey(): FixtureMonkey {
            return CONSTRUCT_BASED_FIXTURE_MONKEY
        }


        @JvmStatic
        fun getReflectionBasedFixtureMonkey(): FixtureMonkey {
            return REFLECTION_BASED_FIXTURE_MONKEY
        }

        @JvmStatic
        fun getJavaTypeBasedFixtureMonkey(): FixtureMonkey {
            return JAVA_TYPE_BASED_FIXTURE_MONKEY
        }


        private fun setUpJavaType(builder: FixtureMonkeyBuilder): FixtureMonkeyBuilder {
            return builder
                .plugin(
                    JqwikPlugin()
                        .javaTypeArbitraryGenerator(object : JavaTypeArbitraryGenerator {
                            override fun strings(): StringArbitrary {
                                return Arbitraries.strings().alpha().numeric().ofMinLength(1).ofMaxLength(10)
                            }

                            override fun longs(): LongArbitrary {
                                return Arbitraries.longs().greaterOrEqual(1L)
                            }

                            override fun integers(): IntegerArbitrary {
                                return Arbitraries.integers().greaterOrEqual(1)
                            }
                        })
                )
        }
    }
}