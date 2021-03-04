<?php

namespace App\Repository;

use App\Entity\CandidatEmploi;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method CandidatEmploi|null find($id, $lockMode = null, $lockVersion = null)
 * @method CandidatEmploi|null findOneBy(array $criteria, array $orderBy = null)
 * @method CandidatEmploi[]    findAll()
 * @method CandidatEmploi[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CandidatEmploiRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, CandidatEmploi::class);
    }

    // /**
    //  * @return CandidatEmploi[] Returns an array of CandidatEmploi objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?CandidatEmploi
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
