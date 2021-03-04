<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210302102833 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE demande_certification DROP FOREIGN KEY FK_DCBB3CC563F29423');
        $this->addSql('DROP INDEX UNIQ_DCBB3CC563F29423 ON demande_certification');
        $this->addSql('ALTER TABLE demande_certification CHANGE id_certif_id certification_id INT NOT NULL');
        $this->addSql('ALTER TABLE demande_certification ADD CONSTRAINT FK_DCBB3CC5CB47068A FOREIGN KEY (certification_id) REFERENCES certification (id)');
        $this->addSql('CREATE INDEX IDX_DCBB3CC5CB47068A ON demande_certification (certification_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE demande_certification DROP FOREIGN KEY FK_DCBB3CC5CB47068A');
        $this->addSql('DROP INDEX IDX_DCBB3CC5CB47068A ON demande_certification');
        $this->addSql('ALTER TABLE demande_certification CHANGE certification_id id_certif_id INT NOT NULL');
        $this->addSql('ALTER TABLE demande_certification ADD CONSTRAINT FK_DCBB3CC563F29423 FOREIGN KEY (id_certif_id) REFERENCES certification (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_DCBB3CC563F29423 ON demande_certification (id_certif_id)');
    }
}
