<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210301215602 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE certification DROP FOREIGN KEY FK_6C3C6D751A867E8F');
        $this->addSql('DROP INDEX IDX_6C3C6D751A867E8F ON certification');
        $this->addSql('ALTER TABLE certification CHANGE id_entreprise_id entreprise_id INT NOT NULL');
        $this->addSql('ALTER TABLE certification ADD CONSTRAINT FK_6C3C6D75A4AEAFEA FOREIGN KEY (entreprise_id) REFERENCES entreprise (id)');
        $this->addSql('CREATE INDEX IDX_6C3C6D75A4AEAFEA ON certification (entreprise_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE certification DROP FOREIGN KEY FK_6C3C6D75A4AEAFEA');
        $this->addSql('DROP INDEX IDX_6C3C6D75A4AEAFEA ON certification');
        $this->addSql('ALTER TABLE certification CHANGE entreprise_id id_entreprise_id INT NOT NULL');
        $this->addSql('ALTER TABLE certification ADD CONSTRAINT FK_6C3C6D751A867E8F FOREIGN KEY (id_entreprise_id) REFERENCES entreprise (id)');
        $this->addSql('CREATE INDEX IDX_6C3C6D751A867E8F ON certification (id_entreprise_id)');
    }
}
