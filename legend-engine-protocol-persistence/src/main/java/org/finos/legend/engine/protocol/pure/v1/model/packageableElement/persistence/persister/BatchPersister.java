package org.finos.legend.engine.protocol.pure.v1.model.packageableElement.persistence.persister;

import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.persistence.persister.ingestmode.IngestMode;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.persistence.persister.targetshape.TargetShape;

public class BatchPersister extends Persister
{
    public TargetShape targetShape;
    public IngestMode ingestMode;

    public <T> T accept(PersisterVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}