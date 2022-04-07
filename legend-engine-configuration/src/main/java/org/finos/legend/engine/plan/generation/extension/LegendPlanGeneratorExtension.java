// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.plan.generation.extension;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.finos.legend.engine.external.shared.format.model.ExternalFormatPlanGenerationExtensionLoader;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.PureModel;
import org.finos.legend.engine.plan.generation.transformers.LegendPlanTransformers;
import org.finos.legend.engine.plan.generation.transformers.PlanTransformer;
import org.finos.legend.engine.plan.generation.transformers.VersionPlanTransformer;
import org.finos.legend.pure.generated.*;

public class LegendPlanGeneratorExtension implements PlanGeneratorExtension {

    @Override
    public MutableList<PlanTransformer> getExtraPlanTransformers()
    {
        return Lists.mutable.withAll(LegendPlanTransformers.transformers).with(new VersionPlanTransformer());
    }

    @Override
    public RichIterable<? extends Root_meta_pure_router_extension_RouterExtension> getExtraRouterExtensions(PureModel pureModel)
    {
        MutableList<Root_meta_pure_router_extension_RouterExtension> pureRouterExtensions = Lists.mutable.empty();

        RichIterable<Root_meta_external_shared_format_ExternalFormatExtension> planGenerationExtensions = LazyIterate.collect(ExternalFormatPlanGenerationExtensionLoader.extensions().values(), ext -> ext.getPureExtension(pureModel.getExecutionSupport()));
        pureRouterExtensions.addAll(core_external_shared_extension.Root_meta_external_shared_format_routerExtensions_String_1__ExternalFormatExtension_MANY__RouterExtension_MANY_("externalFormat", planGenerationExtensions, pureModel.getExecutionSupport()).toList());

        pureRouterExtensions.add(core_servicestore_router_router_extension.Root_meta_external_store_service_router_extension_defaultServiceStoreExtensions__RouterExtension_1_(pureModel.getExecutionSupport()));
        pureRouterExtensions.addAll(core_relational_relational_router_router_extension.Root_meta_pure_router_extension_defaultRelationalExtensions__RouterExtension_MANY_(pureModel.getExecutionSupport()).toList());

        return pureRouterExtensions.toImmutable();
    }

}
